import {useState, useEffect} from 'react';
import {getAllData} from "./client";
import {
    Breadcrumb,
    Layout,
    Menu,
    theme,
    Table,
    Spin,
    Empty,
} from 'antd';
import { LoadingOutlined } from '@ant-design/icons';

import './App.css';

const {Header, Content, Footer} = Layout;
const columns = [
    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Lastname',
        dataIndex: 'lastname',
        key: 'lastname',
    },
    {
        title: 'Email',
        dataIndex: 'email',
        key: 'email',
    },
];

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
function App() {
    const {
        token: {colorBgContainer},
    } = theme.useToken();
    const [users, setUsers] = useState([]);
    const [fetching, setFetching] = useState(true);
    const fetchUsers = () =>
        getAllData()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setUsers(data);
                setFetching(false);
            });

    useEffect(() => {
        console.log("component is mounted");
        fetchUsers();
    }, []);

    const renderUsers = () => {
        if (fetching) {
            return <Spin indicator={antIcon} />
        }
        if (users.length <= 0) {
            return <Empty />;
        }
        return <Table
            dataSource={users}
            columns={columns}
            bordered
            title={() => 'Users'}
            pagination={{ pageSize: 50 }}
            scroll={{ y: 240 }}
            rowKey={(userData) => userData.id}
        />;
    }

    return (
        <Layout>
            <Header
                style={{
                    position: 'sticky',
                    top: 0,
                    zIndex: 1,
                    width: '100%',
                    display: 'flex',
                    alignItems: 'center',
                }}
            >
                <div className="demo-logo"/>
                <Menu
                    theme="dark"
                    mode="horizontal"
                    defaultSelectedKeys={['2']}
                    items={new Array(3).fill(null).map((_, index) => ({
                        key: String(index + 1),
                        label: `nav ${index + 1}`,
                    }))}
                />
            </Header>
            <Content
                className="site-layout"
                style={{
                    padding: '0 50px',
                }}
            >
                <Breadcrumb
                    style={{
                        margin: '16px 0',
                    }}
                >
                    <Breadcrumb.Item>Users</Breadcrumb.Item>
                    <Breadcrumb.Item>List</Breadcrumb.Item>
                </Breadcrumb>
                <div
                    style={{
                        padding: 24,
                        minHeight: 380,
                        background: colorBgContainer,
                    }}
                >
                    {renderUsers()}
                </div>
            </Content>
            <Footer
                style={{
                    textAlign: 'center',
                }}
            >
                Santiago Lanao ©2023
            </Footer>
        </Layout>
    );
}

export default App;
